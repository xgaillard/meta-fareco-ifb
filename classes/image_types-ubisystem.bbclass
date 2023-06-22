inherit image_types

IMAGE_TYPES += "ubisystem"
IMAGE_TYPEDEP_ubisystem = "ubifs"

IMAGE_CMD_ubisystem () {
    cat << EOF > ${WORKDIR}/ubinize.cfg
[root0]
mode=ubi
image=${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.ubifs
vol_id=0
vol_size=128MiB
vol_type=dynamic
vol_name=root0

[data0]
mode=ubi
vol_id=1
vol_size=32MiB
vol_type=dynamic
vol_name=data0

[root1]
mode=ubi
vol_id=2
vol_size=128MiB
vol_type=dynamic
vol_name=root1

[data1]
mode=ubi
vol_id=3
vol_size=32MiB
vol_type=dynamic
vol_name=data1

[journal]
mode=ubi
vol_id=4
vol_type=dynamic
vol_name=journal
vol_flags=autoresize
vol_size=128MiB
EOF

    # Create image
    ubinize -o ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ubi ${UBINIZE_ARGS} ${WORKDIR}/ubinize.cfg

    # Create symlink
    if [ -e ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ubi ]; then
        ln -rsf ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ubi ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.ubi
    fi
}
