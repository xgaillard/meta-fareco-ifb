DESCRIPTION = ""
LICENSE = "CLOSED"

PREMIRRORS = ""
MIRRORS = ""

SRC_URI = "git:///home/xavier/axiomtek-hardknott/work/libyocto/;protocol=file"
#SRC_URI = "git://git@docinfo/data/Vcs/GitRoot/Produits/YOCTO/libyocto.git;protocol=ssh"
SRCREV = "${AUTOREV}"


#SRCREV = "497767bc2a52eb0a0d46caa12c3d9d7ded5f9113"
#SRC_URI = "git://git@github.com:/xgaillard/libyocto.git;protocol=ssh"

inherit pkgconfig

DEPENDS += " systemd glib-2.0"
DEPENDS_append_rbox630 = " i2c-tools libgpiod"
DEPENDS_append_gws501 = ""
DEPENDS_append_radipv3 = " libgpiod"

S = "${WORKDIR}/git"

EXTRA_OEMAKE_append_ifb122 = "MACHINE=IFB"
EXTRA_OEMAKE_append_rbox630 = "MACHINE=RBOX630"
EXTRA_OEMAKE_append_gws501 = "MACHINE=GWS501"
EXTRA_OEMAKE_append_radipv3 = "MACHINE=RADIPV3"

PACKAGES += "${PN}-tools"

RDEPENDS_${PN} += "libyocto-config"
RDEPENDS_${PN}-tools += " ${PN}"

do_install(){
      oe_runmake install prefix=${D}
}

FILES_${PN} = "\
      ${includedir}/* \
      ${libdir}/* \
"

FILES_${PN}-tools =  "\
      ${bindir}/* \
"
