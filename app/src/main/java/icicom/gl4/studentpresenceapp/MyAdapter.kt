package icicom.gl4.studentpresenceapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private val studentsList: ArrayList<Student> ): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= studentsList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.firstname.text=currentItem.firstname
        holder.lastname.text=currentItem.lastname




    }

    override fun getItemCount(): Int {
        return studentsList.size
    }





    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleImage: ImageView =itemView.findViewById(R.id.titleImage)
        val firstname: TextView =itemView.findViewById(R.id.firstname)
        val lastname:TextView=itemView.findViewById(R.id.lastname)


    }

}
