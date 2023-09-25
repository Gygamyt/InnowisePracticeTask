package com.innowise.innowise_practice.api.vk.utils;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.objects.likes.responses.IsLikedResponse;
import com.vk.api.sdk.objects.wall.responses.CreateCommentResponse;
import com.vk.api.sdk.objects.wall.responses.GetCommentResponse;
import com.vk.api.sdk.objects.wall.responses.PostResponse;

public class UtilsMethods {

    public static boolean isPostLiked(VkApiClient vk, UserActor actor, int postId) throws ClientException, ApiException {
        IsLikedResponse isPostLiked = vk
                .likes()
                .isLiked(actor, Type.POST, postId)
                .execute();
        return isPostLiked.isLiked();
    }

    public static AddResponse addLikeToPost(VkApiClient vk, UserActor actor, int postId) throws ClientException, ApiException {
        return vk
                .likes()
                .add(actor, Type.POST, postId)
                .execute();
    }

    public static CreateCommentResponse createComment(VkApiClient vk, UserActor actor, int postId, String comment) throws ClientException, ApiException {
        return vk
                .wall()
                .createComment(actor, postId)
                .message(comment)
                .execute();
    }

    public static PostResponse postWall(VkApiClient vk, UserActor actor, String text) throws ClientException, ApiException {
        return vk
                .wall()
                .post(actor)
                .message(text)
                .execute();
    }

    public static GetCommentResponse getCommentResponse (VkApiClient vk, UserActor actor, int commentId) throws ClientException, ApiException {
        return vk
                .wall()
                .getComment(actor, commentId)
                .execute();
    }
}
