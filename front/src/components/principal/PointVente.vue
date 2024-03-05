<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allPointVente=reactive({});

    const getAllPointVente = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/pointVente/allPointVente';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allPointVente, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idPointVente) => {
        router.push({path : "/modifPointVente", query: {idPointVente : idPointVente}});
    }

    const deletePointVenteById = (idPointVente) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/pointVente/deletePointVenteById?idPointVente='+idPointVente;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllPointVente);
</script>
<template>
    <TitleListe title-liste="Liste des pointVente"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(pointVente)  in allPointVente" :key="pointVente.idPointVente">
            <H5TitleListe labels="" :title-h5="pointVente.idPointVente"/>
            <SpanListe :title-span='pointVente.idPointVente' labels=''/><SpanListe :title-span='pointVente.nomPointVente' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(pointVente.idPointVente)" :funct-delete="() => deletePointVenteById(pointVente.idPointVente)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
