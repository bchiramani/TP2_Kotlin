package icicom.gl4.studentpresenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayListCours: ArrayList<Student>
    private lateinit var newArrayListTp: ArrayList<Student>
    lateinit var imagesId: Array<Int>
    lateinit var firstnames: Array<String>
    lateinit var lastnames: Array<String>
    lateinit var imagesId2: Array<Int>
    lateinit var firstnames2: Array<String>
    lateinit var lastnames2: Array<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagesId = arrayOf(
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.a,
            )
        firstnames = arrayOf(
            "Amani",
            "Fatma",
            "Edem",
            "Arbia",
            "Nouha",
            "Ines",
            "Sirine",
            "Firas"

        )
        lastnames = arrayOf(
            "Bchir",
            "Chaouech",
            "Oueslati",
            "Jlassi",
            "Haboubi",
            "Cherni",
            "Cherni",
            "Cherni"

        )
        imagesId2 = arrayOf(

            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
        )
        firstnames2 = arrayOf(


            "Ines",
            "Sirine",
            "Firas"

        )
        lastnames2 = arrayOf(

            "Cherni",
            "Cherni",
            "Cherni"

        )



        var seance=arrayOf(
            "Cours",
            "TP"
        )



        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.hasFixedSize()
        newArrayListCours = ArrayList<Student>(8)
        newArrayListTp = ArrayList<Student>(3)

        construct(newArrayListCours,imagesId,firstnames,lastnames)
        construct(newArrayListTp,imagesId2,firstnames2,lastnames2)

        var adapterCours = MyAdapter(newArrayListCours)
        newRecyclerView.adapter = adapterCours





        val autoComp=findViewById<AutoCompleteTextView>(R.id.autoComp)
        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,firstnames)
        autoComp.setAdapter(adapter)

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

    }
    fun construct(newArrayList:ArrayList<Student>,imagesId: Array<Int>,firstnames: Array<String>,lastnames: Array<String>) {
        for (i in imagesId.indices) {
            val student = Student(imagesId[i], firstnames[i], lastnames[i])
            newArrayList.add(student)
        }

    }



}
