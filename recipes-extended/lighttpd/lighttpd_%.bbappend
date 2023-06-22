# Remove /www directory, we will use our own

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append() {
    rm -r ${D}/www/
}

FILES_${PN}_remove = "/www"