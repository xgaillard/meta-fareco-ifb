inherit image_types

IMAGE_TYPES += "uuu"
IMAGE_TYPEDEP_uuu = "ubisystem"

do_image_uuu[depends] += " \
    virtual/bootloader:do_deploy \
    virtual/kernel:do_deploy \
"

IMAGE_CMD_uuu () {
    [ -f "${DEPLOY_DIR_IMAGE}/barebox.bin" ] || bbfatal "Missing barebox.bin"
    [ -f "${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.ubi" ] || bbfatal "Missing ${IMAGE_LINK_NAME}.ubi"

    #TODO Add to meta-fareco/uuu ?
    cat << EOF > ${WORKDIR}/uuu.auto
uuu_version 1.2.39

# Configure fastboot
CFG: FB: -chip imx6ull -pid 0x0104 -vid 0x1d6b

# Load the bootloader
SDP: boot -f barebox.bin

# Copy the bootloader
FB: flash barebox barebox.bin

# Flash the bootloader
FB: oem exec barebox_update -y -t nand /barebox.bin

# Erase the bootloader environment
FB: oem exec erase /dev/nand0.barebox-environment.bb

# Reset the bootchooser
FB: oem exec bootchooser -p default system0 system1
FB: oem exec bootchooser -a default system0 system1

# Flash the ubi
FB[-t 60000]: flash ubi ${IMAGE_LINK_NAME}.ubi

FB: done
EOF
#TODO Make sparse work: FB[-t 60000]: flash -raw2sparse $UUU_IMAGE_NAME

    #TODO Better way to clean old file?
    rm -f ${IMGDEPLOYDIR}/uuu-*.zip

    # Create image
    zip -j1 ${IMGDEPLOYDIR}/uuu-${IMAGE_NAME}.zip \
        ${WORKDIR}/uuu.auto \
        ${DEPLOY_DIR_IMAGE}/barebox.bin \
        ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.ubi
        
    # Create symlink
    if [ -e ${IMGDEPLOYDIR}/uuu-${IMAGE_NAME}.zip ]; then
        ln -rsf ${IMGDEPLOYDIR}/uuu-${IMAGE_NAME}.zip ${IMGDEPLOYDIR}/uuu-${IMAGE_LINK_NAME}.zip
    fi
}
