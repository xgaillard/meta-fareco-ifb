inherit extrausers

add_ssl_private() {
    bbnote "Security: adding keys and certificates"

    # ssl
    install -d -m 0710 ${IMAGE_ROOTFS}/etc/security/ssl/private
    install -m 0400 ${SECURITY_PATH}/package/ssl/ssl.key.pem ${IMAGE_ROOTFS}/etc/security/ssl/private/

    install -d -m 0555 ${IMAGE_ROOTFS}/etc/security/ssl/certs
    install -m 0444 ${SECURITY_PATH}/package/ssl/ssl.cert.pem ${IMAGE_ROOTFS}/etc/security/ssl/certs/

}

ROOTFS_POSTPROCESS_COMMAND_append = " \
    add_ssl_private; \
"
