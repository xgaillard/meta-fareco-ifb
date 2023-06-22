FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#FIXME Check everything is working

SRC_URI += " \
    file://network/10-eth0.network \
    file://network/10-eth1.network \
    file://journald.conf  \
"

do_install_append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/network/10-eth0.network ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${WORKDIR}/network/10-eth1.network ${D}${sysconfdir}/systemd/network/

    install -m 0644 ${WORKDIR}/journald.conf ${D}${sysconfdir}/systemd/
}

do_install_append_gws501() {
    install -m 0644 ${WORKDIR}/network/10-eth1.network ${D}${sysconfdir}/systemd/network/
}

FILES_${PN} += "\
    ${sysconfdir} \
    ${systemd_unitdir} \
"
