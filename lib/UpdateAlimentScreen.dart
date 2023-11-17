import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:lab2_try3/Aliment.dart';

class UpdateAlimentScreen extends StatefulWidget{
  const UpdateAlimentScreen({super.key, required this.aliment});

  final Aliment aliment;

  @override
  State<UpdateAlimentScreen> createState() => UpdateAlimentScreenState();
}

class UpdateAlimentScreenState extends State<UpdateAlimentScreen>{
  final _formKey = GlobalKey<FormState>();

  bool isValidDateTime(String s){
    try{
      DateTime.parse(s);
    }catch(e){
      return false;
    }
    return true;
  }

  @override
  Widget build(BuildContext context){
    String name = widget.aliment.name;
    String buy_date = widget.aliment.buy_date.toString();
    String expiration_date = widget.aliment.expiration_date.toString();
    String aliment_quantity = widget.aliment.aliment_quantity;
    String status = widget.aliment.status;

    return Scaffold(
      appBar: AppBar(
        title: Text("Update an aliment"),
        backgroundColor: Colors.green.shade700,
      ),
      body: Container(
        decoration: BoxDecoration(
          color: Colors.black,
        ),
          child: Form(
              key: _formKey,
              child: Column(
                children: [
                  Row(
                    children: [
                      Text("Name:   ", style: TextStyle(color:Colors.white,)),
                      Expanded(child: TextFormField(
                        style: TextStyle(color:Colors.white,),
                        initialValue: name,
                        onChanged: (value) => name = value,
                        validator: (value){
                          if(value == null || value.isEmpty){
                            return "The name field should have a value";
                          }
                          return null;
                        },
                      ))
                    ],
                  ),
                  Row(
                    children: [
                      Text("Buy Date:   ", style: TextStyle(color:Colors.white,)),
                      Expanded(child: TextFormField(
                        style: TextStyle(color:Colors.white,),
                        initialValue: buy_date,
                        onChanged: (value) => buy_date = value,
                        validator: (value){
                          if(value == null || value.isEmpty || !isValidDateTime(value)){
                            return "The buy date field should have a valid value";
                          }
                          return null;
                        },
                      ))
                    ],
                  ),
                  Row(
                    children: [
                      Text("Expiration Date:   ", style: TextStyle(color:Colors.white,)),
                      Expanded(child: TextFormField(
                        style: TextStyle(color:Colors.white,),
                        initialValue: expiration_date,
                        onChanged: (value) => expiration_date = value,
                        validator: (value){
                          if(value == null || value.isEmpty || !isValidDateTime(value)){
                            return "The expiration date field should have a valid value";
                          }
                          return null;
                        },
                      ))
                    ],
                  ),
                  Row(
                    children: [
                      Text("Aliment quantity:   ", style: TextStyle(color:Colors.white,)),
                      Expanded(child: TextFormField(
                        style: TextStyle(color:Colors.white,),
                        initialValue: aliment_quantity,
                        onChanged: (value) => aliment_quantity = value,
                        validator: (value){
                          if(value == null || value.isEmpty){
                            return "The aliment quantity field should have a value";
                          }
                          return null;
                        },
                      ))
                    ],
                  ),
                  Row(
                    children: [
                      Text("Status:   ", style: TextStyle(color:Colors.white,)),
                      Expanded(child: TextFormField(
                        style: TextStyle(color:Colors.white,),
                        initialValue: status,
                        onChanged: (value) => status = value,
                        validator: (value){
                          if(value == null || value.isEmpty){
                            return "The status field should have a value";
                          }
                          return null;
                        },
                      ))
                    ],
                  ), SizedBox(height: 20),
                  ElevatedButton(onPressed: (){
                    if (_formKey.currentState!.validate()){
                      Navigator.pop(context, Aliment(
                          name,
                          DateTime.parse(buy_date),
                          DateTime.parse(expiration_date),
                          aliment_quantity,
                          status
                      ));

                    }
                  }, child: const Text('Update', style: TextStyle(color:Color.fromARGB(255, 0, 100, 0),))),
                ],
              )
          )
      ),

    ) ;

  }
}