DESCRIPTION = "Logging library for C applications"
HOMEPAGE = "https://github.com/babelouest/rhonabwy"
LICENSE = "LGPL-2.1"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1-or-later;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
"

SRC_URI = "git://github.com/babelouest/rhonabwy;protocol=https"
#v1.1.6
#FIXME Test upgrade to 1.1.8
SRCREV = "775a8e82719571ab8328a0782bcf40133ec65bfd"

S = "${WORKDIR}/git"

inherit cmake

DEPENDS = "nettle gnutls jansson zlib curl systemd yder"

#EXTRA_OECMAKE += "-DWITH_CURL=on"

do_install_append() {
    #find ${D} -type f -name 'orcania*' -delete
}
