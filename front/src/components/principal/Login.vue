<script setup>
    import { onMounted, reactive, getCurrentInstance, ref, watch  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';
    import { useRoute } from 'vue-router';
    import Pagination from '../reuse/Pagination.vue';

    const allLogin=reactive({});
    const nbLoginOnePage=ref(0);
    const listeNumPages=reactive([]);
    const route=useRoute();

    const getAllLogin = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/login/allLogin/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allLogin, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const countLoginOnePage = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/login/countLoginOnePage';
            const response = await axios.get(urlApiSpringBoot, { headers });
            nbLoginOnePage.value=response.data;
            for(var i=1; i<=nbLoginOnePage.value; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (id) => {
        router.push({path : "/modifLogin", query: {id : id}});
    }

    const deleteLoginById = (id) => {
        try {
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/login/deleteLoginById?id='+id;
            const response = axios.get(urlApiSpringBoot, { headers });
            getAllLogin();   
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(() =>  {
        if(!localStorage.getItem("tokenLogin")) {
            router.push("/");
        }
        getAllLogin();
        countLoginOnePage();
    });

    watch(() => route.params.numPage, () => {
        getAllLogin();
    });
</script>
<template>
    <TitleListe title-liste="Liste des login"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(login)  in allLogin.content" :key="login.id">
            <H5TitleListe labels="" :title-h5="login.id"/>
            <SpanListe :title-span='login.id' labels=''/><SpanListe :title-span='login.mail' labels=''/><SpanListe :title-span='login.mdp' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(login.id)" :funct-delete="() => deleteLoginById(login.id)"/>
        </div>
    </ListeGenerale>
    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" url="allLogin"/>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
