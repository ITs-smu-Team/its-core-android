package com.example.mlkit_pose.fragment.expre

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.companion.JSP
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.fragment_my_routine.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RoutineFragment : Fragment() {

    private var id: String? = null
    private var param2: String? = null
    lateinit var adapter: ItemAdapter

    //체크된 내용 기억
    val checkedItemList = ArrayList<String>()  // 선택된 항목을 담는 리스트
    val RoutineList = ArrayList<String>()  // 선택된 항목을 담는 리스트
    val RoutinesportsList = ArrayList<String>()  // 선택된 항목을 담는 리스트
    var recyclerView: CustomRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("ROUTINE_FRAGMENT","onCreate")
        arguments?.let {
            id = it.getString("id")
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("ROUTINE_FRAGMENT","onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_routine, container, false)

    }
    fun setAdapter(){
        Log.v("ROUTINE_FRAGMENT","setAdapter")
        adapter = ItemAdapter(id, context,parentFragmentManager)
        if (recyclerView != null) {
            recyclerView!!.adapter = adapter
        }
        recyclerView?.addItemDecoration(DividerItemDecoration(context))
        if (recyclerView != null) {
            recyclerView!!.layoutManager = ItemLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.v("ROUTINE_FRAGMENT","onViewCreate")
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recylcerview) as CustomRecyclerView
        setAdapter()
        add_routineButton.setOnClickListener {
            val queue = Volley.newRequestQueue(context)
            val url = JSP.Companion.getExercise()
            val stringRequest = StringRequest(Request.Method.GET,url,{ response ->
                val datas = response.split("@")
                var newRoutineName: String = ""
                val (itemArray,itemEnArray) = setItemList(datas)
                val checkedItems = BooleanArray(22)
                /* Exercise Check Part */
                val setExercise: AlertDialog.Builder = AlertDialog.Builder(context)
                    .setTitle("CheckList Test")
                setExercise.setMultiChoiceItems(itemArray, checkedItems) { dialog, which, isChekced ->
                    checkedItems[which] = isChekced
                }
                setExercise.setPositiveButton("확인", DialogInterface.OnClickListener() { dialog, which ->

                    var texts: String = ""
                    var engtexts: String = ""
                    for (i in 0 until itemArray.size) {
                        val checked = checkedItems[i]
                        if (checked) {
                            texts += "${itemArray[i]},"
                            engtexts += "${itemEnArray[i]},"
                        }
                    }
                    texts = texts.substring(0, (texts.length) - 1)
                    engtexts = engtexts.substring(0, (engtexts.length) - 1)
                    setRoutine(id.toString(), newRoutineName, texts, engtexts)

                    val routineAdapter: ItemAdapter = recyclerView?.adapter as ItemAdapter
                    val childItems: List<String> = texts.split(",");
                    routineAdapter.addItems(childItems, newRoutineName);
                    Log.d("ROUTINE_LIST", childItems.toString())
                    dialog.dismiss()
                    // write down volley code here

                    Log.d("ROUTINE_LIST", texts)
                    Log.d("ROUTINE_LIST", engtexts)
                })
                    .setNegativeButton("취소", DialogInterface.OnClickListener() { dialog, which ->
                        dialog.dismiss()
                    })
                setExercise.create()
                val checkOverlap: AlertDialog.Builder = AlertDialog.Builder(context)
                    .setTitle("루틴 이름 중복")
                    .setMessage("다른 이름으로 설정해주세요.")
                    .setPositiveButton(
                        "확인",
                        DialogInterface.OnClickListener { dialog, which ->
                            dialog.dismiss()
                        })
                checkOverlap.create()
                /* Input Routine Name Part */
                val inputName = layoutInflater.inflate(R.layout.popup_routine_add, null)
                val setRoutineName: AlertDialog.Builder = AlertDialog.Builder(context)
                    .setView(inputName)
                    .setPositiveButton("확인", DialogInterface.OnClickListener() { dialog, which ->
                        val routineAdapter: ItemAdapter = recyclerView?.adapter as ItemAdapter

                        newRoutineName =
                            inputName.findViewById<EditText>(R.id.editRoutineName).text.toString()
                        // write down volley code here
                        if (routineAdapter.checkRoutineName(newRoutineName)) {
                            checkOverlap.show()
                        }
                        else{
                            setExercise.show()
                        }
                        Log.d("ROUTINE_SET", "inputName : $newRoutineName")
                        dialog.dismiss()
                    })
                    .setNegativeButton("취소", DialogInterface.OnClickListener() { dialog, which ->
                        dialog.dismiss()
                    })
                setRoutineName.create().show()
            },{
                Log.d("ROUTINE_POPUP","Volley Error")
            })
            queue.add(stringRequest)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.v("ROUTINE_FRAGMENT","onViewStateRestored")
    }
    private fun setRoutine(
        id: String,
        routineName: String,
        exerciseName: String,
        exerciseEnName: String
    ) {
        val queue = Volley.newRequestQueue(context)
        val url_setRoutine = JSP.setRoutineIns(id, routineName, exerciseName, exerciseEnName)
        val StringRequest = StringRequest(Request.Method.GET, url_setRoutine, { response ->
            response.trim { it <= ' ' }
        }, {
            Toast.makeText(context, "sever error", Toast.LENGTH_SHORT).show()
        })
        queue.add(StringRequest)
    }
    fun setItemList(excData:List<String>): Pair<Array<String>, Array<String>> {
        val itemArray : ArrayList<String> = ArrayList<String>()
        val itemEnArray : ArrayList<String> = ArrayList<String>()
        for(i in 0 until (excData.size -1)){
            val excItems = excData[i].split(",")
            itemArray.add(excItems[0])
            itemEnArray.add(excItems[1])
        }
        return Pair(itemArray.toTypedArray(),itemEnArray.toTypedArray())
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoutineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }


}