import 'package:flutter/material.dart';
import 'package:lab2_try3/Aliment.dart';

class DeleteAlimentScreen extends StatelessWidget{
  const DeleteAlimentScreen({super.key, required this.aliment});

  final Aliment aliment;

  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text("Delete aliment"),
        backgroundColor: Colors.green.shade700,
      ),
      body: Container(
        decoration: BoxDecoration(
          color: Colors.black,
        ),
        child:Column(
          children: [
            Text(
                "\nDelete \n\n${aliment.name} \n\nBought on: ${aliment.buy_date} \n\nExpires on: ${aliment.expiration_date}\n", style: TextStyle(color:Colors.white,)
            ),
            Row (
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                Container(
                  color: Colors.white,
                  width: 110,
                  height: 35,
                  child: TextButton(
                      onPressed: (){
                        Navigator.pop(context, aliment);
                      },
                      child: Text("Delete", style: TextStyle(color:Color.fromARGB(255, 0, 100, 0),))
                  ),
                ),
                Container(
                  color: Colors.white,
                  width: 110,
                  height: 35,
                  child: TextButton(
                    onPressed: (){
                      Navigator.pop(context, null);
                    },
                    child: Text("Cancel", style: TextStyle(color:Color.fromARGB(255, 0, 100, 0),)),
                  ),
                ),
              ],
            )
          ],
        ),
      )
    );
  }
}