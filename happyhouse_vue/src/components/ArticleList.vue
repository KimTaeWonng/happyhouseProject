<template>
    <div>
    <b-table
        striped
        hover
        :items="qnaList"
        :per-page="perPage"
        :current-page="currentPage"
        :fields="fields"
        @row-clicked="rowClick"
    ></b-table>
    <b-pagination v-model="currentPage" :total-rows="qnaListLength" :per-page="perPage" align="center"></b-pagination>
    <b-button @click="writeContent">글쓰기</b-button>
    </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
import router from "@/router";
export default {
    name: 'Project1ArticleList',

    data() {
        return {
            currentPage : 1,
            perPage : 5,
            fields: [
                {
                key: "articleno",
                label: "번호"
                },
                {
                key: "subject",
                label: "제목"
                },
                {
                key: "userid",
                label: "글쓴이"
                },
                {
                key: "regtime",
                label: "작성일"
                }
            ],
        };
    },

    mounted() {
        
    },

    methods: {
        async rowClick(target){
            console.log(target);
            console.log(target.articleno);
            await this.$store.dispatch("setQnaSelect", target.articleno)
            router.push('/detail');
            
        },
        writeContent(){

        }
    },

    computed: {
        ...mapState(["qnaList"]),
        ...mapGetters(["qnaListLength"])
    },

    async created() {
        this.$store.dispatch("setQnaList");
    },
};
</script>

<style lang="scss" scoped>

</style>