create or replace view analysis.account_creds_unit as (
	select 
		(count(*)-count(distinct user_pass)) = 0 as user_pass_unique
	from argonotes.account_creds
);

create or replace view analysis.accounts_unit as (
	select
		count(case when a.cnt > 1 then 1 else 0 end) = 0 as is_valid
	from (
		select
			count(*) as cnt
		from argonotes.accounts
		group by user_acc, user_pass
    ) a
);

create or replace view analysis.cluster_assoc_unit as (
	select
		(count(*)-count(cluster_id)) = 0 as id_not_null,
		(count(*)-count(participant)) = 0 as participant_not_null
	from argonotes.cluster_assoc
);

create or replace view analysis.clusters_unit as (
	select
		(count(*)-count(distinct cluster_id)) = 0 as ids_unique,
        (count(*)-count(cluster_id)) = 0 as ids_not_null,
        (count(*)-count(cluster_owner)) = 0 as owners_full
    from argonotes.clusters
);

create or replace view analysis.devices_unit as (
	select 
		(count(*)-count(distinct authorized_MAC)) = 0 as device_mac_unique,
        (count(*)-count(authorized_MAC)) = 0 as device_mac_not_null,
        (count(*)-count(assoc_user))=0 as users_not_null
    from argonotes.devices
);

create or replace view analysis.notes_unit as (
	select
		(count(*)-count(distinct note_id)) = 0 as note_id_unique,
        (count(*)-count(note_id)) = 0 as note_id_not_null,
        (count(*)-count(cluster_id)) = 0 as all_cluster_assoc,
        (count(*)-count(user_id)) = 0 as all_have_user,
        (count(case when is_collab and collab_id is not null then 1 else 0 end)-count(is_collab)) = 0 as all_collabs_valid
    from argonotes.notes
);

create or replace view analysis.users_unit as (
	select
		(count(*)-count(distinct uid)) = 0 as uid_unique,
        (count(*)-count(first_name))=0 as fname_not_null,
        (count(*)-count(last_name)) = 0 as lname_not_null,
        (count(*)-count(role_id)) = 0 as role_not_null
    from argonotes.users
);