DESCRIPTION = "Logging library for C applications"
HOMEPAGE = "https://github.com/babelouest/yder"
SECTION = "libs"
LICENSE = "LGPL-2.1"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1-or-later;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
"


SRC_URI = "git://github.com/babelouest/yder;protocol=https"

S = "${WORKDIR}/git"
#v1.4.17
SRCREV = "28206b26635abcaab47e81984f891c9f74b5bcff"

inherit cmake

DEPENDS = "systemd orcania"
