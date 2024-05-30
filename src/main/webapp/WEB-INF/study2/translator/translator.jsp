<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>translator.jsp</title>
  <%@ include file = "/include/bs4.jsp" %>
	<script src="https://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
	<link rel="stylesheet" type="text/css" href="${ctp}/css/flags.css">
  <script type="text/javascript">
    /* 구글 번역 초기화 */
    function googleTranslateElementInit() {new google.translate.TranslateElement({pageLanguage: 'ko',autoDisplay: false}, 'google_translate_element');}
    /* 새 UI 선택 클릭 이벤트가 발생하면
    감춤 처리한 구글 번역 콤보리스트에
    선택한 언어를 적용해 변경 이벤트를 발생시키는 코드  */
    $(function() {
      document.querySelector('.translation-links').addEventListener('click',function(event) {
        let el = event.target;
        if(el != null){
          while(el.nodeName == 'FONT' || el.nodeName == 'SPAN'){el = el.parentElement;}//data-lang 속성이 있는 태그 찾기
          const tolang = el.dataset.lang; // 변경할 언어 코드 얻기
          const gtcombo = document.querySelector('.goog-te-combo');
          if (gtcombo == null) {
            alert("Error: Could not find Google translate Combolist.");
            return false;
          }
          gtcombo.value = tolang; // 변경할 언어 적용
          gtcombo.dispatchEvent(new Event('change')); // 변경 이벤트 트리거
        }
        document.body.style.cssText = "";
        return false;
      });
    });
  
	  function transforLanguage() {
		  let originalLanguage = $("#originalLanguage").val();
		  demo.innerText = originalLanguage;
	  }
	  
	  function contentClear() {
		  originalLanguage.innerText = "";
	  }
	</script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<jsp:include page="/include/nav.jsp" />
<p><br/></p>
<div class="container">
	<div id="google_translate_element" style="display:none;"></div>
  <!-- "새 번역 링크 UI" -->
  <ul class="translation-links">
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="korea" data-lang="ko" title="한국어"><span class="flag ko"></span><span>Korea</span></a></li>
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="english" data-lang="en" title="English"><span class="flag en"></span><span>English</span></a></li>
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="japanese" data-lang="ja" title="日本語"><span class="flag ja"></span><span>Japanese</span></a></li>
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="chinese" data-lang="zh-CN" title="中文(简体)"><span class="flag zh-CN"></span><span>Chinese(Simplified)</span></a></li>
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="taiwanese" data-lang="zh-TW" title="中文(繁體)"><span class="flag zh-TW"></span><span>taiwanese(Traditional)</span></a></li>
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="spanish" data-lang="es" title="español"><span class="flag es"></span><span>Spanish</span></a></li>
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="german" data-lang="de" title="Deutsch"><span class="flag de"></span><span>German</span></a></li>
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="russian" data-lang="ru" title="Русский язык"><span class="flag ru"></span><span>Russian</span></a></li>
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="portuguese" data-lang="pt" title="Português"><span class="flag pt"></span><span>Portuguese</span></a></li>
      <li><a href="javascript:void(0)" onclick="transforLanguage()" class="french" data-lang="fr" title="français"><span class="flag fr"></span><span>French</span></a></li>
  </ul>
  <div style="clear:both;" class="m-3">&nbsp;</div>
  <hr/>
  <div class="row">
    <div class="col"><input type="text" value="원본글내용" disabled /></div>
    <div class="col text-right"><input type="text" value="다시입력" onclick="contentClear()" readonly class="btn btn-secondary btn-sm" /></div>
  </div>
  <div class="mt-2 mb-4">
    <textarea rows="4" id="originalLanguage" class="form-control">번역할 원문을 입력하세요</textarea>
  </div>
  <div><input type="text" value="번역내용" disabled /></div>
  <div id="demo"></div>
  
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>