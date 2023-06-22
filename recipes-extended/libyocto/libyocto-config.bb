DESCRIPTION = ""
LICENSE = "CLOSED"

SRC_URI = " \
	file://chrony/fareco.conf \
	file://wpa_supplicant/wpa_supplicant-wired-eth0.conf \
    file://systemd/chronyd.service.d/90-fareco.conf \
    file://systemd/systemd-journal-upload.service.d/90-fareco.conf \
	file://systemd/wpa_supplicant-wired@.service.d/90-fareco.conf \
"

RDEPENDS_${PN} += "\
    chrony \
    wpa-supplicant \
"

FARECO_CONF_DIR = "${sysconfdir}/fareco"

do_install(){
    install -d ${D}${FARECO_CONF_DIR}
    install -d ${D}${FARECO_CONF_DIR}/services

    # chrony
    install -d ${D}${FARECO_CONF_DIR}/chrony.d
    install -m 0644 ${WORKDIR}/chrony/fareco.conf ${D}${FARECO_CONF_DIR}/chrony.d/

    install -d ${D}${systemd_system_unitdir}/chronyd.service.d
    install -m 0644 ${WORKDIR}/systemd/chronyd.service.d/90-fareco.conf ${D}${systemd_system_unitdir}/chronyd.service.d/

    # journal-upload
    install -d ${D}${systemd_system_unitdir}/systemd-journal-upload.service.d
    install -m 0644 ${WORKDIR}/systemd/systemd-journal-upload.service.d/90-fareco.conf ${D}${systemd_system_unitdir}/systemd-journal-upload.service.d

    # wpa_supplicant-wired
    install -d ${D}${systemd_system_unitdir}/wpa_supplicant-wired@.service.d
    install -m 0644 ${WORKDIR}/systemd/wpa_supplicant-wired@.service.d/90-fareco.conf ${D}${systemd_system_unitdir}/wpa_supplicant-wired@.service.d

    install -d ${D}${systemd_system_unitdir}/multi-user.target.wants
    ln -sr ${D}${systemd_system_unitdir}/wpa_supplicant-wired@.service ${D}${systemd_system_unitdir}/multi-user.target.wants/wpa_supplicant-wired@eth0.service

    install -d ${D}${FARECO_CONF_DIR}/wpa_supplicant
    install -m 0644 ${WORKDIR}/wpa_supplicant/wpa_supplicant-wired-eth0.conf ${D}${FARECO_CONF_DIR}/wpa_supplicant/
}

FILES_${PN} = "\
    ${FARECO_CONF_DIR}\
    ${systemd_system_unitdir}\
"
