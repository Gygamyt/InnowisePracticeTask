package com.innowise.innowise_practice.api.vk;

import com.innowise.innowise_practice.ui.utils.PropReader;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.base.responses.OkResponse;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.objects.wall.responses.CreateCommentResponse;
import com.vk.api.sdk.objects.wall.responses.GetCommentResponse;
import com.vk.api.sdk.objects.wall.responses.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.innowise.innowise_practice.api.vk.utils.UtilsMethods.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VKTest {

    private static final Integer APP_ID = 51471875;

    private static final String COMMENT = "zachem ya eto delayu";

    private static final String POST_TEXT = "api testi...";

    @Disabled
    @Test
    public void test() throws ClientException, ApiException {
        TransportClient transportClient = new HttpTransportClient();

        VkApiClient vk = new VkApiClient(transportClient);

        UserActor actor = new UserActor(APP_ID, PropReader.getProperty("vk_access_token"));

        PostResponse postOnWall = postWall(vk, actor, POST_TEXT);

        int postId = postOnWall.getPostId();

        AddResponse addLike = addLikeToPost(vk, actor, postId);

        CreateCommentResponse commentResponse = createComment(vk, actor, postId, COMMENT);

        int commentId = commentResponse.getCommentId();

        GetCommentResponse getCommentResponse = getCommentResponse(vk, actor, commentId);

        Assertions.assertAll(
                () -> assertTrue(isPostLiked(vk, actor, postId)),
                () -> assertTrue(getCommentResponse.toPrettyString().contains(COMMENT))
        );

        OkResponse deleteResponse = vk
                .wall()
                .delete(actor)
                .postId(postId)
                .execute();
    }
}
