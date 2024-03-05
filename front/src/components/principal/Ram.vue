<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allRam=reactive({});

    const getAllRam = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/ram/allRam';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allRam, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idRam) => {
        router.push({path : "/modifRam", query: {idRam : idRam}});
    }

    const deleteRamById = (idRam) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/ram/deleteRamById?idRam='+idRam;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllRam);
</script>
<template>
    <TitleListe title-liste="Liste des ram"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(ram)  in allRam" :key="ram.idRam">
            <H5TitleListe labels="" :title-h5="ram.idRam"/>
            <SpanListe :title-span='ram.idRam' labels=''/><SpanListe :title-span='ram.capacite' labels=''/><SpanListe :title-span='ram.frequence' labels=''/><SpanListe :title-span='ram.idTypeRam.label' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(ram.idRam)" :funct-delete="() => deleteRamById(ram.idRam)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
