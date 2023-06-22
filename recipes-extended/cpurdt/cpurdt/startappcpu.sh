#!/bin/sh
# Auteur : xavier
# Date initiale : 24/07/2020
# startappc4s.sh,v
# ------------------------------------------
# Le fichier constitue le script de lancement de premier niveau qui n'est 
# pas modifie par la procedure de mise a jour.
#
# ------------------------------------------
#
# Revision 1.1.2.3  2020/07/24 09:32:48  xg
# Pour voir la substitution des mots cles.
#
#
if [ -d /home/root/update ]  ; \
	then \
	(cd /home/root; rm -rf archive) ;\
	(cd /srv/fareco/ ; rm -rf archive) ;\
	if [ -e /srv/fareco/current ] ;\
		then (cd /srv/fareco;  mv current archive) ;\
		(cd /srv/fareco;  mkdir current) ;\
	fi ;\
	if [ -e /home/root/current ] ;\
		then (cd /home/root;  mv current archive) ;\
	fi ;\
	(cd /home/root/update ; mv www /srv/fareco/current ) ;\
	(cd /home/root ; mv update current ) ;\
fi;
