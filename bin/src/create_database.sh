#!/bin/bash

# Exécute la commande SQLite3
sqlite3 my_database.db < Creer_Tables.sql

# Affiche les tables de la base de données
echo "Tables de la base de données :"
sqlite3 my_database.db ".tables"
