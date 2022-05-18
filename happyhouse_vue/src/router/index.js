import Vue from 'vue';
import VueRouter from 'vue-router';
import ArticleList from '@/components/ArticleList.vue';
import ArticleDetail from '@/components/ArticleDetail.vue';
import ArticleEdit from '@/components/ArticleEdit.vue';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'articlelist',
		component: ArticleList
	},
	{
		path: '/detail',
		name: 'articleDetail',
		component: ArticleDetail
	},
	{
		path: '/edit/:type',
		name: 'articleEdit',
		component: ArticleEdit
	}
];

const router = new VueRouter({
	routes
});

export default router;
