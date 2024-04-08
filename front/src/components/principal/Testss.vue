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

    const allTestss=reactive({});
    const nbTestssOnePage=ref(0);
    const listeNumPages=reactive([]);
    const route=useRoute();

    const getAllTestss = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/testss/allTestss/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allTestss, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const countTestssOnePage = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/testss/countTestssOnePage';
            const response = await axios.get(urlApiSpringBoot, { headers });
            nbTestssOnePage.value=response.data;
            for(var i=1; i<=nbTestssOnePage.value; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idtestss) => {
        router.push({path : "/modifTestss", query: {idtestss : idtestss}});
    }

    const deleteTestssById = (idtestss) => {
        try {
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/testss/deleteTestssById?idtestss='+idtestss;
            const response = axios.get(urlApiSpringBoot, { headers });
            getAllTestss();   
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(() =>  {
        if(!localStorage.getItem("tokenLogin")) {
            router.push("/");
        }
        getAllTestss();
        countTestssOnePage();
    });

    watch(() => route.params.numPage, () => {
        getAllTestss();
    });
</script>
<template>
    <TitleListe title-liste="Liste des testss"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(testss)  in allTestss.content" :key="testss.idtestss">
            <H5TitleListe labels="" :title-h5="testss.idtestss"/>
            <SpanListe :title-span='testss.idtestss' labels=''/><SpanListe :title-span='testss.nomtestss' labels=''/><SpanListe :title-span='testss.idtest.label' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(testss.idtestss)" :funct-delete="() => deleteTestssById(testss.idtestss)"/>
        </div>
    </ListeGenerale>
    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" url="allTestss"/>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
