
class Aliment {
  String name;
  DateTime buy_date;
  DateTime expiration_date;
  String aliment_quantity;
  String status;

  Aliment(this.name, this.buy_date, this.expiration_date, this.aliment_quantity, this.status);
}

class AlimentId{
  String name;
  DateTime buy_date;
  DateTime expiration_date;

  AlimentId(Aliment aliment): name = aliment.name, buy_date = aliment.buy_date, expiration_date = aliment.expiration_date;

  bool is_equal(AlimentId other){
    return name == other.name && buy_date == other.buy_date && expiration_date == other.expiration_date;
  }
}