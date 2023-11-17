
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:lab2_try3/AddAlimentScreen.dart';
import 'package:lab2_try3/Aliment.dart';
import 'package:lab2_try3/DeleteAlimentScreen.dart';
import 'package:lab2_try3/Repository.dart';
import 'package:lab2_try3/UpdateAlimentScreen.dart';

class ListScreen extends StatefulWidget{
  const ListScreen({super.key});

  @override
  State<ListScreen> createState() => ListScreenState();

}

class ListScreenState extends State<ListScreen>{
  Repository repository = Repository();
  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text("Aliment manager app"),
        backgroundColor: Colors.green.shade700,
      ),
      body: Container(
        decoration: BoxDecoration(
          color: Colors.black,
        ),
        child: Column(
          children: [
            Container(
                width: 600,
                child: TextButton(
                    onPressed: () async{
                      Aliment aliment = await Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => AddAlimentScreen())
                      )as Aliment;
                      if(!repository.add(aliment)){
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text('The element you are trying to add already exists')),
                        );
                      }else{
                        setState((){});
                      }
                    },
                    child: Text("Add", style: TextStyle(color:Colors.lightGreen[200],))
                )
            ),
            Expanded(
              child: ListView.builder(
                  itemCount: repository.getAll().length,
                  itemBuilder: (context, index){
                    return alimentView(index, repository.getAll()[index]);
                  }),
            )
          ],
        ),
      ),
    );
  }

  Widget alimentView(int index, Aliment aliment){
    return Card(
      color: Colors.lightGreen[200],
      child: Column(
        children: [
          Text("\n${index + 1}. ${repository.getAll()[index].name} \nbought on: ${repository.getAll()[index].buy_date} \nexpieres on: ${repository.getAll()[index].expiration_date}\nquantity: ${repository.getAll()[index].aliment_quantity}\nstatus: ${repository.getAll()[index].status}", style: TextStyle(fontSize: 18)),
          SizedBox(height: 20),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Container(
                color: Colors.white70,
                width: 110,
                height: 35,
                child: TextButton(
                  onPressed: () async{
                    Aliment aliment = await Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => UpdateAlimentScreen(aliment: repository.getAll()[index]))
                    )as Aliment;
                    if(!repository.update(aliment)){
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(content: Text('The element you are trying to update does not exist')),
                      );
                    }else{
                      setState(() {});
                    }
                  },
                child: Text("Update", style: TextStyle(color:Colors.green.shade700,)),
                ),
              ),
                Container(
                  color: Colors.white70,
                  width: 110,
                  height: 35,
                  child: TextButton(
                  onPressed: () async{
                  Aliment aliment = await Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => DeleteAlimentScreen(aliment: repository.getAll()[index]))
                  )as Aliment;
                  if(!repository.remove(aliment)){
                    ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('The element you are trying to delete does not exist')),
                  );
                 }else{
                    setState(() {});
                  }
                  },
                  child: Text("Delete", style: TextStyle(color:Colors.green.shade700,)),
               ),
              )
            ],

          ), SizedBox(height: 20),
        ],
      )
    );
  }
}

