DESCRIPTION = ""
LICENSE = "CLOSED"

inherit systemd

PREMIRRORS = ""
MIRRORS = ""

#SRC_URI = "gitsm://git@docinfo:/data/Vcs/GitRoot/Produits/Rdt/appRdt.git;branch=newWeb;protocol=ssh"
SRC_URI = "gitsm://git@git.fareco.cloud.nxo.fr/be-meyreuil/produits/rdt/app.git;branch=newWeb;protocol=ssh"
#SRCREV = "r20230911_cpurdtparking_01030001_02000300"
#SRCREV = "r20231005_cpurdtparking_01030001_02000400"
#SRCREV = "r20230724_cpurdtparking_01020100_02000101"
#SRCREV = "r20231610_cpurdtparking_01030002_02000400"
#SRCREV = "r20231020_cpurdtparking_01030003_02000400"
#SRCREV = "r20231109_cpurdtparking_01030004_02000400"
#SRCREV = "r20231128_cpurdtparking_01030100_01000400"
SRCREV = "r20231221_cpurdtparking_01030101_02000400"
#SRC_URI = " \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb;branch=newWeb;protocol=file \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb/generation;protocol=file;branch=uncrustify;destsuffix=git/generation \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb/cpuRdtCommon;branch=newWeb;protocol=file;destsuffix=git/cpuRdtCommon \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb/cpu432Board;protocol=file;destsuffix=git/cpu432Board \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb/cpu432Tools;protocol=file;destsuffix=git/cpu432Tools \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb/cpuRdtComm;protocol=file;destsuffix=git/cpuRdtComm \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb/cpuRdtProtocol;protocol=file;destsuffix=git/cpuRdtProtocol \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb/kcommon;protocol=file;destsuffix=git/kcommon \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb/klog;protocol=file;destsuffix=git/klog \
#	git:///home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb/kxml;protocol=file;destsuffix=git/kxml \
#"

#SRCREV_default = "${AUTOREV}"

#	file://cpurdt.service \
#    file://radsirius-web.service \
#

SRC_URI += " \
    file://lighttpd/cpurdt.conf \
    file://cpurdt.service \
    file://cpurdt-web.service \
    file://startappcpu.sh \
"

PACKAGES += "${PN}-webserver"

DEPENDS += "libaxeapi libaxeapi libyocto"
RDEPENDS_${PN} += "libaxeapi libyocto-tools"

DEPENDS += " ulfius rhonabwy"
RDEPENDS_${PN}-webserver += " lighttpd lighttpd-module-openssl lighttpd-module-proxy"

SYSTEMD_PACKAGES += "${PN}-webserver"

SYSTEMD_SERVICE_${PN} = "\
    ${PN}.service \
"
SYSTEMD_SERVICE_${PN}-webserver = "\
    ${PN}-web.service \
"

SYSTEMD_AUTO_ENABLE_${PN}-webserver = "enable"

EXTRA_OEMAKE += "\
CIBLE=ifb \
VARIANTE=PARKING \
DEP_DISABLED=1 \
NO_EXTRA_WARNING=1 \
"

APP_BUILD_DIR = "${WORKDIR}/git"
PARALLEL_MAKE = ""
TARGET_CC_ARCH += "${LDFLAGS} -lEApi"

do_compile() {
    cd ${APP_BUILD_DIR}/cpuRdt
    oe_runmake build
}

LOCAL_ROOT_DIR="/home/root"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -d ${D}${LOCAL_ROOT_DIR}/current/usr/sbin

    # cpurdt
    install -m 0755 ${APP_BUILD_DIR}/cpuRdt/build/PARKING/ifb/bin/cpuRdtMain ${D}${LOCAL_ROOT_DIR}/current/usr/sbin/${PN}

    install -d ${D}${LOCAL_ROOT_DIR}/data
    install -m 0644 ${WORKDIR}/${PN}.service ${D}${systemd_system_unitdir}/

    # Script de mise a jour.
    install -m 0777 ${WORKDIR}/startappcpu.sh ${D}${LOCAL_ROOT_DIR}/

    # cpurdt-web
    install -m 0755 ${APP_BUILD_DIR}/cpuRdt/build/PARKING/ifb/bin/c4busybox ${D}${LOCAL_ROOT_DIR}/current/usr/sbin/${PN}-web

    install -m 0644 ${WORKDIR}/${PN}-web.service ${D}${systemd_system_unitdir}/
    install -m 0640 ${APP_BUILD_DIR}/cpuRdtWebBack/bdd.txt ${D}${LOCAL_ROOT_DIR}/data/

    # lighttpd
    install -d ${D}${sysconfdir}/lighttpd.d/
    install -m 0640 ${WORKDIR}/lighttpd/cpurdt.conf ${D}${sysconfdir}/lighttpd.d/

    install -d ${D}${servicedir}/fareco/current/www/
    cp -r ${APP_BUILD_DIR}/cpuRdtWebFront/* ${D}${servicedir}/fareco/current/www/
}

FILES_${PN} = "\
     ${LOCAL_ROOT_DIR}/startappcpu.sh \
     ${LOCAL_ROOT_DIR}/data \
     ${LOCAL_ROOT_DIR}/current/usr/sbin/${PN} \
     ${systemd_system_unitdir}/${PN}.service \
"

FILES_${PN}-webserver =  "\
    ${LOCAL_ROOT_DIR}/current/usr/sbin/${PN}-web \
    ${LOCAL_ROOT_DIR}/data/bdd.txt \
    ${sysconfdir}/lighttpd.d \
    ${servicedir}/fareco/current/www \
    ${systemd_system_unitdir}/${PN}-web.service \
"

