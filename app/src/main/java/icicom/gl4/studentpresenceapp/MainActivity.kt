package icicom.gl4.studentpresenceapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE
import android.widget.Switch


class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView

    private lateinit var newArrayList: ArrayList<Student>
    private lateinit var newArrayListCours: ArrayList<Student>
    private lateinit var newArrayListTp: ArrayList<Student>
    private lateinit var  newArrayListCoursAbsent: ArrayList<Student>
    private lateinit var  newArrayListCoursPresent: ArrayList<Student>
    private lateinit var  newArrayListTpPresent: ArrayList<Student>
    private lateinit var  newArrayListTpAbsent: ArrayList<Student>


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var seance=arrayOf(
            "Cours",
            "TP"
        )
        var absence=arrayOf(
            "Present",
            "Absent"
        )



        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.hasFixedSize()
        newArrayList = ArrayList<Student>()
        newArrayListCours = ArrayList<Student>()
        newArrayListTp = ArrayList<Student>()
        newArrayListCoursAbsent=ArrayList<Student>()
        newArrayListCoursPresent=ArrayList<Student>()
        newArrayListTpAbsent=ArrayList<Student>()
        newArrayListTpPresent=ArrayList<Student>()

        newArrayList.add(Student("amani","bchir","F",TRUE,TRUE,TRUE,TRUE))
        newArrayList.add(Student("fatma","chaouech ","F",FALSE,FALSE,TRUE,TRUE))
        newArrayList.add(Student("edem","oueslati","M",TRUE,TRUE,FALSE,FALSE))
        newArrayList.add(Student("firas","cherni","M",TRUE,TRUE,FALSE,FALSE))
        newArrayList.add(Student("ines","cherni","F",TRUE,TRUE,TRUE,TRUE))
        newArrayList.add(Student("sirin","cherni","F",TRUE,TRUE,TRUE,TRUE))
        newArrayList.add(Student("ahmed","ahmed","F",TRUE,TRUE,TRUE,TRUE))


        for (student in newArrayList){
            if (student.cours){
                newArrayListCours.add(student)
            }
            if(student.tp){
                newArrayListTp.add(student)
            }
        }

        var adapterCours = MyAdapter(newArrayListCours)
        newRecyclerView.adapter = adapterCours


        var adapterTp = MyAdapter(newArrayListTp)
        newRecyclerView.adapter = adapterTp






        val autoComp=findViewById<AutoCompleteTextView>(R.id.autoComp)
        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,newArrayList)
        autoComp.setAdapter(adapter)

        val presenceSwitch = findViewById<Switch>(R.id.presenceSwitch)

        presenceSwitch?.setOnCheckedChangeListener({ _ , isChecked ->

            if(isChecked){

                Toast.makeText(this@MainActivity, "checked", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this@MainActivity, "not checked", Toast.LENGTH_SHORT).show()

            }
        })


        val spinner: Spinner = findViewById(R.id.spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
                R.array.seance,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }




        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{




            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val spinnerChoice=seance.get(p2)
                Toast.makeText(this@MainActivity,spinnerChoice,Toast.LENGTH_SHORT).show()
                if (spinnerChoice=="Cours"){
                    var adapterCours = MyAdapter(newArrayListCours)

                    newRecyclerView.adapter = adapterCours
                }

                else {
                    var adapterTp = MyAdapter(newArrayListTp)

                    newRecyclerView.adapter = adapterTp
                }


            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }




        val absenceSpinner: Spinner = findViewById(R.id.absenceSpinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.absence,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            absenceSpinner.adapter = adapter
        }
        absenceSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{




            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val spinnerChoice=absence.get(p2)
                for (student in newArrayListCours){
                    if (student.coursPres){
                        newArrayListCoursPresent.add(student)
                    }else{
                        newArrayListCoursAbsent.add(student)
                    }

                }
                for (student in newArrayListTp){
                    if (student.tpPres){
                        newArrayListTpPresent.add(student)
                    }else{
                        newArrayListTpAbsent.add(student)
                    }

                }

                var adapterCours = MyAdapter(newArrayListCours)
                newRecyclerView.adapter = adapterCours


                var adapterTp = MyAdapter(newArrayListTp)
                newRecyclerView.adapter = adapterTp

                Toast.makeText(this@MainActivity,spinnerChoice,Toast.LENGTH_SHORT).show()
                if (spinnerChoice=="absent"){
                    var adapterCoursAbsent = MyAdapter(newArrayListCoursAbsent)

                    newRecyclerView.adapter = adapterCoursAbsent
                }

                else {
                    var adapterCoursPresent = MyAdapter(newArrayListCoursPresent)

                    newRecyclerView.adapter = adapterCoursPresent
                }


            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }



    }




}
