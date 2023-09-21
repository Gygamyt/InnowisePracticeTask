package com.innowise.innowise_practice.api;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.base.responses.OkResponse;
import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.objects.likes.responses.DeleteResponse;
import com.vk.api.sdk.objects.wall.responses.CreateCommentResponse;
import com.vk.api.sdk.objects.wall.responses.GetCommentResponse;
import com.vk.api.sdk.objects.wall.responses.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.innowise.innowise_practice.api.utils.UtilsMethods.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VKTest {

    private static final Integer APP_ID = 51471875;

    private static final String COMMENT = "zachem ya eto delayu";

    private static final String POST_TEXT = "api testi...";

    @Test
    public void test() throws ClientException, ApiException {
        TransportClient transportClient = new HttpTransportClient();

        VkApiClient vk = new VkApiClient(transportClient);

        UserActor actor = new UserActor(APP_ID, "vk1.a.CwfwYEyUmz4AYLlqFHZftHMk37awgUZ9cq_zhkEUQHpfUQh0K0j_BdqvbaoHyldtA8-f9Fs_Qz46OWXyzCZKgEEDsUSTB4uolVM4MFZxUHKam4XDtyEiLKA7gtg200mgwjHtUjCTfoaBZdxuSoKoSBv5H1bgwmRF-By2Nh0IUc8Ew07t5y5YJAmvsmJHkJyjcjlKxgTJ5PSvlVHEV-lryg");

        PostResponse postOnWall = postWall(vk, actor, POST_TEXT);

        int postId = postOnWall.getPostId();

        AddResponse addLike = vk
                .likes()
                .add(actor, Type.POST, postId)
                .execute();

        CreateCommentResponse commentResponse = createComment(vk, actor, postId, COMMENT);

        int commentId = commentResponse.getCommentId();

        GetCommentResponse getCommentResponse = vk
                .wall()
                .getComment(actor, commentId)
                .execute();

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
