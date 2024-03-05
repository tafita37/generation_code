<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allLaptop=reactive({});

    const getAllLaptop = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/allLaptop';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allLaptop, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idLaptop) => {
        router.push({path : "/modifLaptop", query: {idLaptop : idLaptop}});
    }

    const deleteLaptopById = (idLaptop) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/laptop/deleteLaptopById?idLaptop='+idLaptop;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllLaptop);
</script>
<template>
    <TitleListe title-liste="Liste des laptop"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(laptop)  in allLaptop" :key="laptop.idLaptop">
            <H5TitleListe labels="" :title-h5="laptop.idLaptop"/>
            <SpanListe :title-span='laptop.idLaptop' labels=''/><SpanListe :title-span='laptop.model' labels=''/><SpanListe :title-span='laptop.idMarque.label' labels=''/><SpanListe :title-span='laptop.idProcesseur.label' labels=''/><SpanListe :title-span='laptop.idRam.label' labels=''/><SpanListe :title-span='laptop.tailleEcran' labels=''/><SpanListe :title-span='laptop.idDisque.label' labels=''/><SpanListe :title-span='laptop.prixAchat' labels=''/><SpanListe :title-span='laptop.prixVente' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(laptop.idLaptop)" :funct-delete="() => deleteLaptopById(laptop.idLaptop)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
