import 'package:flutter/material.dart';
import 'package:lab2_try3/ListScreen.dart';

void main() {
  runApp(const MaterialApp(
    home: Directionality(textDirection: TextDirection.ltr, child: ListScreen())
  ));
}

