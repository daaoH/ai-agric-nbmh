package com.hszn.nbmh.third.utils.signature.factory.response;

import com.hszn.nbmh.third.utils.signature.bean.Attachments;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/30 10:14
 * @version 
 */
public class QryAttachmentsResponse extends Response{
    private Attachments attachments;

    public Attachments getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }
}
