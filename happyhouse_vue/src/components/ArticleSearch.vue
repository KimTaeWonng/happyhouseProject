<template>
  <div>
    <b-container fluid>
      <b-row>
        <b-col sm="3">
        </b-col>
        <b-col sm="5">
          <b-form-input v-model="keyword" placeholder="Enter your name"></b-form-input>
        </b-col>
        <b-col sm="1">
          <b-button @click="search" @keydown.enter="search">검색</b-button>
        </b-col>
        <b-col sm="3">
      </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
    name: 'HappyhouseVueArticleSearch',

    data() {
        return {
            keyword : ""
        };
    },

    mounted() {
        
    },

    methods: {
        async search(){
          console.log(this.keyword);
          await this.$store.dispatch("searchQnaList", this.keyword);
        }
    },
};
</script>

<style lang="scss" scoped>

</style>