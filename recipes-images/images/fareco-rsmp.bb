DESCRIPTION = "This is the standart FARECO image for ifb122 with rsmp"

require recipes-fsl/images/imx-image-core.bb
require security.inc
require version.inc

IMAGE_INSTALL += " \
    lighttpd lighttpd-module-openssl lighttpd-module-proxy \
    libyocto ulfius rhonabwy cpurdt cpurdt-webserver \
    rsmp \
"





