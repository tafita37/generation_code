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

    const allTest=reactive({});
    const nbTestOnePage=ref(0);
    const listeNumPages=reactive([]);
    const route=useRoute();

    const getAllTest = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/test/allTest/'+numPage;
            const response = await axios.get(urlApiSpringBoot, { headers });
            Object.assign(allTest, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const countTestOnePage = async () => {
        try {
            const numPage=route.params.numPage;
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/test/countTestOnePage';
            const response = await axios.get(urlApiSpringBoot, { headers });
            nbTestOnePage.value=response.data;
            for(var i=1; i<=nbTestOnePage.value; i++) {
                listeNumPages.push(i);
            }
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idtest) => {
        router.push({path : "/modifTest", query: {idtest : idtest}});
    }

    const deleteTestById = (idtest) => {
        try {
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const urlApiSpringBoot = 'http://localhost:8080/test/deleteTestById?idtest='+idtest;
            const response = axios.get(urlApiSpringBoot, { headers });
            getAllTest();   
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(() =>  {
        if(!localStorage.getItem("tokenLogin")) {
            router.push("/");
        }
        getAllTest();
        countTestOnePage();
    });

    watch(() => route.params.numPage, () => {
        getAllTest();
    });
</script>
<template>
    <TitleListe title-liste="Liste des test"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(test)  in allTest.content" :key="test.idtest">
            <H5TitleListe labels="" :title-h5="test.idtest"/>
            <SpanListe :title-span='test.idtest' labels=''/><SpanListe :title-span='test.nomtest' labels=''/><SpanListe :title-span='test.label' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(test.idtest)" :funct-delete="() => deleteTestById(test.idtest)"/>
        </div>
    </ListeGenerale>
    <Pagination :actual-num-page="route.params.numPage" :liste-num-pages="listeNumPages" url="allTest"/>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
