validateLogin
===
select * from user_info where 1=1 and username=#username# and pwd=#pwd#

update
===
update user set
@if(!isEmpty(nickname)){
nickname=#nickname#,
@}
@if(!isEmpty(face)){
face=#face#,
@}
@
if(!isEmpty(sign){
}){
sign=#sign#
}@
where id=#id#;