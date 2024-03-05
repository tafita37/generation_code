<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allProcesseur=reactive({});

    const getAllProcesseur = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/processeur/allProcesseur';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allProcesseur, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idProcesseur) => {
        router.push({path : "/modifProcesseur", query: {idProcesseur : idProcesseur}});
    }

    const deleteProcesseurById = (idProcesseur) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/processeur/deleteProcesseurById?idProcesseur='+idProcesseur;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllProcesseur);
</script>
<template>
    <TitleListe title-liste="Liste des processeur"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(processeur)  in allProcesseur" :key="processeur.idProcesseur">
            <H5TitleListe labels="" :title-h5="processeur.idProcesseur"/>
            <SpanListe :title-span='processeur.idProcesseur' labels=''/><SpanListe :title-span='processeur.nomProcesseur' labels=''/><SpanListe :title-span='processeur.frequence' labels=''/><SpanListe :title-span='processeur.nbCoeur' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(processeur.idProcesseur)" :funct-delete="() => deleteProcesseurById(processeur.idProcesseur)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
