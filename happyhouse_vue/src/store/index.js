import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import router from '@/router';
Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		user: null,
		qnaList: [],
		qnaSelect: null
	},
	getters: {
		qnaListLength: function (state) {
			return state.qnaList.length;
		}
	},
	mutations: {
		SET_QNA_LIST: function (state, qnaList) {
			state.qnaList = qnaList;
		},
		SET_QNA_SELECT: function (state, qnaSelect) {
			state.qnaSelect = qnaSelect;
		},
		CLEAR_QNA_SELECT: function (state) {
			state.qnaSelect = {
				content: '',
				subject: '',
				userid: 'ssafy'
			};
		}
	},
	actions: {
		setQnaList: async function ({ commit }) {
			let res = await axios({
				url: 'http://localhost:9999/vue/board',
				method: 'GET'
			});
			console.log('SET_QNA_LIST', res.data);
			commit('SET_QNA_LIST', res.data);
		},
		setQnaSelect: async function ({ commit }, id) {
			const res = await axios({
				url: 'http://localhost:9999/vue/board/' + id,
				method: 'GET'
			});
			commit('SET_QNA_SELECT', res.data);
			// return axios({
			// 	url: 'http://localhost:9999/vue/board/' + id,
			// 	method: 'GET'
			// }).then(res=>{
			//     console.log('SET_QNA_SELECT', res.data);
			//     commit('SET_QNA_SELECT', res.data);
			// }).catch(err =>{
			//     console.log("Internal Error: " , err);
			// });
		},
		addQna: async function (state, qna) {
			try {
				const res = await axios.post('http://localhost:9999/vue/board/', qna);
				console.log(res.data);
				if (res.data == 'success') {
					alert('등록 성공!');
					router.push('/');
				} else {
					throw 'Server Internal Error';
				}
			} catch (error) {
				alert(error);
			}
		},

		updateQna: async function (state, qna) {
			try {
				const res = await axios.put('http://localhost:9999/vue/board/' + qna.articleno, qna);
				console.log(res.data);
				if (res.data == 'success') {
					alert('수정 완료!');
					router.push('/');
				} else {
					throw 'Server Internal Error';
				}
			} catch (error) {
				alert(error);
			}
		},
		deleteQna: async function (state, id) {
			try {
				const res = await axios.delete('http://localhost:9999/vue/board/' + id);
				console.log(res.data);
				if (res.data == 'success') {
					alert('수정 완료!');
					router.push('/');
				} else {
					throw 'Server Internal Error';
				}
			} catch (error) {
				alert(error);
			}
		},

		searchQnaList : async function({commit}, keyword){
			try {
				const res = await axios.get('http://localhost:9999/vue/board/search/' + keyword);
				if (res.data) {
					commit('SET_QNA_LIST', res.data);
				} else {
					throw 'Server Internal Error';
				}
			} catch (error) {
				alert(error);
			}
		}
	},
	modules: {}
});
