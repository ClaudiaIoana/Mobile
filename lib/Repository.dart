
import 'package:lab2_try3/Aliment.dart';

class Repository{
  final List<Aliment> aliments = [];

  Repository(){
    aliments.add(
      Aliment(
          "Milk",
          DateTime.parse("2023-10-27T10:20:30"),
          DateTime.parse("2023-11-27T10:20:30"),
          "2 l",
          "Excellent"
      )
    );
    aliments.add(
      Aliment(
          "Bread",
          DateTime.parse("2023-10-28T10:20:30"),
          DateTime.parse("2023-11-28T10:20:30"),
          "30 g",
          "Good"
      )
    );
    aliments.add(
      Aliment(
          "Apples",
          DateTime.parse("2023-11-01T10:20:30"),
          DateTime.parse("2023-11-15T10:20:30"),
          "4.5 kg",
          "Excellent"
      )
    );
  }


  bool add(Aliment aliment){
    AlimentId aliemnt_id = AlimentId(aliment);
    for(var value in aliments){
      if(AlimentId(value).is_equal(aliemnt_id)){
        return false;
      }
    }
    aliments.add(aliment);
    return true;
  }

  bool remove(Aliment aliment){
    AlimentId aliment_id = AlimentId(aliment);
    for(var value in aliments){
      if(AlimentId(value).is_equal(aliment_id)){
        aliments.remove(aliment);
        return true;
      }
    }
    return false;
  }

  bool update(Aliment aliment){
    AlimentId aliment_id = AlimentId(aliment);
    for(int i = 0; i < aliments.length; i++){
      var value = aliments[i];
      if(AlimentId(value).is_equal(aliment_id)){
        aliments[i] = aliment;
        return true;
      }
    }
    return false;
  }

  List<Aliment> getAll(){
    return aliments;
  }
}