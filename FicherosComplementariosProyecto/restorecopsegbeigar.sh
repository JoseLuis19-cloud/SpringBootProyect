
#!/bin/bash
echo "Ejecutamos la restauracion de la copia de seguridad"


var=$(date +"%FORMAT_STRING")
now=$(date +"%m_%d_%Y")
printf "%s\n" $now
today=$(date +"%Y_%m_%d")

echo today

/Applications/XAMPP/bin/mysql -u root -proot19 springboot < BeigarBD_2021_05_07_10_22_02.sql