DESCRIPTION = "Logging library for C applications"
HOMEPAGE = "https://github.com/babelouest/yder"
SECTION = "libs"
LICENSE = "LGPL-2.1"


LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1-or-later;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
"


SRC_URI = "git://github.com/babelouest/ulfius;protocol=https"
#v2.7.10
SRCREV = "ee2ef2952b2e90b892eccba0fd4e109f62876cf6"

S = "${WORKDIR}/git"

inherit cmake

DEPENDS = "gnutls libmicrohttpd jansson systemd yder"
