DESCRIPTION = "This is the standart FARECO image for ifb122"

require recipes-fsl/images/imx-image-core.bb
require security.inc
require version.inc

IMAGE_INSTALL += " \
    lighttpd lighttpd-module-openssl lighttpd-module-proxy \
    libyocto ulfius rhonabwy cpurdt cpurdt-webserver \
"

IMAGE_BASENAME = "${PN}"



