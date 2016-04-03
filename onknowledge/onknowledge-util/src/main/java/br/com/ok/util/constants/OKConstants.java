package br.com.ok.util.constants;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public enum OKConstants {
	GLOBAL_CONSTANTS;

	/**
	 * Global String contants
	 */
	public static final String ONKNOWLEDGE = "onknowledge";
	public static final String APOSTROFO = "'";
	public static final String UNDERSCORE = "_";
	public static final String ASPAS = "\"";
	public static final String BARRA = "\\";
	public static final String BARRA_NORMAL = "/";
	public static final String VIRGULA = ",";
	public static final String PARENTESES_INICIAL = "(";
	public static final String PARENTESES_FINAL = ")";
	public static final String CHAVES_INICIAIS = "{";
	public static final String CHAVES_FINAIS = "}";
	public static final String PERCENT = "%";
	public static final String STRING_VAZIA = "";
	public static final String WHITE_SPACE = " ";
	public static final String SEPARADOR_LINHA = "\n";
	public static final String WORDS_SEPARATOR = " - ";
	public static final String DOIS_PONTOS = ":";
	public static final String PONTO_FINAL = ".";
	public static final String PONTO_VIRGULA = ";";
	public static final String SINAL_ATRIBUICAO = "=";
	public static final String EXTENSAO_JPG = "jpg";
	public static final String EXTENSAO_BMP = "bmp";
	public static final String EXTENSAO_PNG = "png";
	public static final String IMG_BASE64_PREFIX = "data:;base64,";
	public static final String PRETTY_PREFIX = "pretty:";
	public static final String SELECT_STRING = "select ";
	public static final String ORDER_BY = " order by ";
	public static final String COUNT_STRING = "count(obj)";
	public static final String AMERICA_SAO_PAULO_TIME_ZONE = "BET";
	public static final String UTF_8_ENCODING = "UTF-8";
	public static final String ISO_8859_1_ENCODING = "ISO-8859-1";
	public static final String TEXT_HTML_CONTENT_TYPE = "text/html";
	public static final String UTF_8_CHARSET = ";charset=UTF-8";
	public static final String AND = " and ";
	public static final String WHERE = " where ";
	public static final String MULTIPLE = "multiple";
	public static final String SINGLE = "single";
	public static final String VALUE = "value";
	public static final String ANEXOS = "anexos";
	public static final String ID = "id";
	public static final String BASE_MAIL_TEMPLATE_PATH = "/WEB-INF/email/templates";

	public static final String STACK_TRACE_KEY = "javax.servlet.error.stack";
	public static final String CONTENT_DISPOSITION_KEY = "content-disposition";
	public static final String EDITING_USER_KEY = "br.com.ok.editingUser";
	public static final String SAVED_SCRIPTS_TO_EXECUTE_KEY = "br.com.ok.savedScriptsToExecute";
	public static final String USER_ID_KEY = "br.com.ok.userId";
	public static final String DISCIPLINA_ID_KEY = "br.com.ok.disciplinaId";
	public static final String CURSO_ID_KEY = "br.com.ok.cursoId";
	public static final String ATIVIDADE_ID_KEY = "br.com.ok.atividadeId";
	public static final String TURMA_ID_KEY = "br.com.ok.turmaId";
	public static final String DISCIPLINA_KEY = "br.com.ok.disciplina";
	public static final String PROFESSORES_SELECIONADOS_KEY = "br.com.ok.professoresSelecionados";
	public static final String DISCIPLINAS_SELECIONADAS_KEY = "br.com.ok.disciplinasSelecionadas";
	public static final String TURMAS_SELECIONADAS_KEY = "br.com.ok.turmasSelecionadas";
	public static final String CURSOS_SELECIONADOS_KEY = "br.com.ok.cursosSelecionados";
	public static final String CALLER_PAGE_KEY = "br.com.ok.callerPage";
	public static final String CALLER_OBJECT_KEY = "br.com.ok.callerObject";
	public static final String TARGET_BEAN = "br.com.ok.targetBean";
	public static final String SOURCE_BEAN = "br.com.ok.sourceBean";
	public static final String CALLER_SELECTION_MODE_KEY = "br.com.ok.callerSelectionMode";
	public static final String SELECTION_MODE_KEY = "br.com.ok.selectionMode";
	public static final String SCRIPTS_TO_EXECUTE_KEY = "br.com.ok.scriptsToExecute";
	public static final String DEFINED_ENCODING_KEY = "br.com.ok.encoding";
	public static final String EVENT_SOURCE_KEY = "javax.faces.source";
	public static final String SPRING_SECURITY_LAST_EXCEPTION_KEY = "SPRING_SECURITY_LAST_EXCEPTION";

	public static final String VELOCITY_TEMPLATE_KEY = "template";
	public static final String VELOCITY_SERVER_URL_KEY = "serverURL";
	public static final String VELOCITY_MAIN_TEMPLATE = "main.vm";

	public static final LocalDateTime MINIMUM_DATE_TIME = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0);
	public static final ZonedDateTime MINIMUM_ZONED_DATE_TIME = ZonedDateTime.of(OKConstants.MINIMUM_DATE_TIME, ZoneId.systemDefault());

	/**
	 * Global Integer contants
	 */
	public static Integer VALOR_ZERO = 0;
	public static Integer VALOR_UM = 1;
	public static Integer VALOR_DOIS = 2;
	public static Integer VALOR_TRES = 3;
	public static Integer VALOR_OITO = 8;
	public static Integer VALOR_DEZ = 10;
	public static Integer VALOR_CEM = 100;
	public static Integer DEFAULT_PROFILE_PICTURE_HEIGHT = 400;

	/**
	 * Regex e Patterns
	 */
	public static final String REGEX_CHAVES = "[\\{|\\}]";
	public static final String REGEX_COLCHETES = "[\\[|\\]]";
	public static final String REGEX_COLCHETES_ASPAS = "[\\[|\\|\"]]";
	public static final String REGEX_PONTO_FINAL = "\\.";

	public static final String PATTERN_DATE_TIME = "dd/MM/yyyy hh:MM:ss";
	public static final String PATTERN_EN_US_DATE = "yyyy-MM-dd";

	/**
	 * Rewrite Actions
	 */
	public static final String PRETTY_ERROR_PAGE = "pretty:500";
	public static final String PRETTY_LOGIN = "pretty:login";
	public static final String PRETTY_SIGNUP = "pretty:signup";
	public static final String PRETTY_ADMIN_SEARCH = "pretty:adminSearch";
	public static final String PRETTY_TEACHER_SEARCH = "pretty:teacherSearch";
	public static final String PRETTY_STUDENT_SEARCH = "pretty:studentSearch";
	public static final String PRETTY_USER_SIGNUP = "pretty:userSignup";
	public static final String PRETTY_EDIT_PROFILE = "pretty:editProfile";
	public static final String PRETTY_DASHBOARD = "pretty:dashboard";
	public static final String PRETTY_VIEW_PROFILE = "pretty:viewProfile";
	public static final String PRETTY_DISCIPLINE_SEARCH = "pretty:disciplineSearch";
	public static final String PRETTY_DISCIPLINE_REGISTER = "pretty:disciplineRegister";
	public static final String PRETTY_CLASSES_SEARCH = "pretty:classesSearch";
	public static final String PRETTY_CLASS_REGISTER = "pretty:classRegister";
	public static final String PRETTY_COURSE_SEARCH = "pretty:courseSearch";
	public static final String PRETTY_COURSE_REGISTER = "pretty:courseRegister";
	public static final String PRETTY_COURSE_DISCIPLINES = "pretty:courseDisciplines";
	public static final String PRETTY_COURSE_CLASSES = "pretty:courseClasses";
	public static final String PRETTY_COURSE_INFORMATIONS = "pretty:courseInformations";
	public static final String PRETTY_ACTIVITY_REGISTER = "pretty:activityRegister";
	public static final String PRETTY_ACTIVITY_SEARCH = "pretty:activitySearch";
	public static final String PRETTY_START = "pretty:start";
}
