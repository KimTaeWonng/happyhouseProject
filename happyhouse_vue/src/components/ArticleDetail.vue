<template>
    <div>
        <b-card>
        <div v-if="qnaSelect" class="content-detail-content-info">
            <div class="content-detail-content-info-left">
            <div class="content-detail-content-info-left-number">{{qnaSelect.articleno}}</div>
            <div class="content-detail-content-info-left-subject">{{qnaSelect.subject}}</div>
            </div>
            <div class="content-detail-content-info-right">
            <div class="content-detail-content-info-right-user">글쓴이: {{qnaSelect.userid}}</div>
            <div class="content-detail-content-info-right-created">등록일: {{qnaSelect.regtime}}</div>
            </div>
        </div>
        <!-- <div class="content-detail-content">{{context}}</div>
        <div class="content-detail-button">
            <b-button variant="primary" @click="updateData">수정</b-button>&nbsp;
            <b-button variant="success" @click="deleteData">삭제</b-button>
        </div> -->
        </b-card>
    </div>
</template>

<script>
import {mapState} from "vuex";
export default {
    name: 'Project1ArticleDetail',

    data() {
        return {
        };
    },

    computed: {
        ...mapState(["qnaSelect"])
    },
    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style lang="scss" scoped>

</style>