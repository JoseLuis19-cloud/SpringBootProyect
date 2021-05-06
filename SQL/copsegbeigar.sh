
#!/bin/bash
echo "Ejecutamos copia de seguridad"


var=$(date +"%FORMAT_STRING")
now=$(date +"%m_%d_%Y")
printf "%s\n" $now
today=$(date +"%Y_%m_%d")

echo today

/Applications/XAMPP/bin/mysqldump -u root -proot19 springboot > /Users/UsuarioJoseLuis/Documents/BeigarBD_${today}.sql
         