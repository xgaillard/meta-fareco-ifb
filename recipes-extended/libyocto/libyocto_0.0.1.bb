DESCRIPTION = ""
LICENSE = "CLOSED"

#PREMIRRORS = "\
#	git://git@docinfo/data/Vcs/GitRoot/Produits/YOCTO/libyocto.git;protocol=ssh \
#	file:///home/xavier/axiomtek-hardknott/work/libyocto.git/ \n \
#"

MIRRORS += "\
	git://git@docinfo/data/Vcs/GitRoot/Produits/YOCTO/libyocto.git;protocol=ssh \
	file:///home/xavier/axiomtek-hardknott/work/libyocto.git/ \n \
"

#SRC_URI = "git:///home/xavier/axiomtek-hardknott/work/libyocto.git"
#SRC_URI = "git://git@docinfo/data/Vcs/GitRoot/Produits/YOCTO/libyocto.git;protocol=ssh"
#SRCREV = "${AUTOREV}"
SRCREV = "0c56f2880de9b48a25ce61dc5e39356fb6857005"
SRC_URI = "git://git@git.fareco.cloud.nxo.fr/be-meyreuil/systems/YOCTO/libyocto.git;protocol=ssh"

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
