<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allTypeRam=reactive({});

    const getAllTypeRam = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/typeRam/allTypeRam';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allTypeRam, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idTypeRam) => {
        router.push({path : "/modifTypeRam", query: {idTypeRam : idTypeRam}});
    }

    const deleteTypeRamById = (idTypeRam) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/typeRam/deleteTypeRamById?idTypeRam='+idTypeRam;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllTypeRam);
</script>
<template>
    <TitleListe title-liste="Liste des typeRam"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(typeRam)  in allTypeRam" :key="typeRam.idTypeRam">
            <H5TitleListe labels="" :title-h5="typeRam.idTypeRam"/>
            <SpanListe :title-span='typeRam.idTypeRam' labels=''/><SpanListe :title-span='typeRam.nomTypeRam' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(typeRam.idTypeRam)" :funct-delete="() => deleteTypeRamById(typeRam.idTypeRam)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
