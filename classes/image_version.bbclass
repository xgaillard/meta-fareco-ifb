
python __anonymous() {
    if d.getVar('IMAGE_VERSION'):
        bb.fatal('IMAGE_VERSION already set')

    imageName = d.getVar('PN')
    versionFlags = d.getVarFlags('IMAGE_VERSION')

    if versionFlags:
        for flag in versionFlags:
            if imageName.split('-')[0] == flag:
                d.setVar('IMAGE_VERSION', d.getVarFlag('IMAGE_VERSION', flag))
                break

    if not d.getVar('IMAGE_VERSION'):
        bb.fatal('IMAGE_VERSION not found for: ', imageName)
}
