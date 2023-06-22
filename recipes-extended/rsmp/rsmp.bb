DESCRIPTION = "Passerelle rsmp equipement"
LICENSE = "CLOSED"

MIRRORS = ""

#FIXME Should compile from git repo

SRC_URI = " \
    file://RsmpEquipment.jar;unpack=0 \
    file://rsmp.service \
    file://Configuration.properties \
"

inherit systemd 

SYSTEMD_SERVICE_${PN} = "rsmp.service"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${bindir}/
    install -m 0755 ${WORKDIR}/RsmpEquipment.jar ${D}${bindir}/

    install -d ${D}${sysconfdir}/fareco/rsmp/resources
    install -m 0644 ${WORKDIR}/Configuration.properties ${D}${sysconfdir}/fareco/rsmp/resources/

    install -m 0755 -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/rsmp.service ${D}${systemd_system_unitdir}
}

FILES_${PN} = "\
    ${bindir} \
    ${sysconfdir}/fareco/rsmp/resources \
    ${systemd_system_unitdir} \
"
