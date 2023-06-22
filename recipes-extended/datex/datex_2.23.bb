DESCRIPTION = "Interface Datex V2"
LICENSE = "CLOSED"

PREMIRRORS = "${FARECO_MIRROR_V2X}"
MIRRORS = ""

#FIXME Should compile from git repo
SRC_URI = "https://v2x.files.fareco.com/interfaceDatex_${PV}.tar.gz;name=interfaceDatex"
SRC_URI[interfaceDatex.md5sum] = "94131f820bf9094608a3174c556f1534"

SRC_URI += " \
    file://datex.service \
    file://datex2.properties \
"

inherit systemd 

SYSTEMD_SERVICE_${PN} = "datex.service"

RDEPENDS_${PN} += "its-services"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${bindir}/
    install -m 0755 ${WORKDIR}/interfaceDatex.jar ${D}${bindir}/

    install -d ${D}${DISTRO_FARECO_RWDIR_CONF}/datex/
    install -m 0644 ${WORKDIR}/datex2.properties ${D}${DISTRO_FARECO_RWDIR_CONF}/datex/

    install -m 0755 -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/datex.service ${D}${systemd_system_unitdir}
}

FILES_${PN} = "\
    ${bindir} \
    ${DISTRO_FARECO_RWDIR_CONF}/datex \
    ${systemd_system_unitdir} \
"
