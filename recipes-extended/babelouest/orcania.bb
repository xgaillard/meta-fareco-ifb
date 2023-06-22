DESCRIPTION = "Potluck with different functions for different purposes that can be shared among C programs."
HOMEPAGE = "https://github.com/babelouest/orcania"
SECTION = "libs"
LICENSE = "LGPL-2.1"
#LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"
#
#
#
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1-or-later;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
"

SRC_URI = "git://github.com/babelouest/orcania.git;protocol=https"
#v2.3.0
SRCREV = "f103652ef15a2e522679e63565ea270c34ee0380"

S = "${WORKDIR}/git"

inherit cmake
