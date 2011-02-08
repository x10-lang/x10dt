#ifndef __X10_LANG_FINISHSTATE__STATEFULREDUCER_H
#define __X10_LANG_FINISHSTATE__STATEFULREDUCER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Reducible;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 

template<class FMGL(T)> class FinishState__StatefulReducer;
template <> class FinishState__StatefulReducer<void>;
template<class FMGL(T)> class FinishState__StatefulReducer : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Reducible<FMGL(T)> > FMGL(reducer);
    
    FMGL(T) FMGL(result);
    
    x10aux::ref<x10::lang::Rail<FMGL(T) > > FMGL(resultRail);
    
    x10aux::ref<x10::lang::Rail<x10_boolean > > FMGL(workerFlag);
    
    void _constructor(x10aux::ref<x10::lang::Reducible<FMGL(T)> > r);
    
    static x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> > _make(
             x10aux::ref<x10::lang::Reducible<FMGL(T)> > r);
    
    virtual void accept(FMGL(T) t);
    virtual void accept(FMGL(T) t, x10_int id);
    virtual void placeMerge();
    virtual FMGL(T) result();
    virtual void reset();
    virtual x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >
      x10__lang__FinishState__StatefulReducer____x10__lang__FinishState__StatefulReducer__this(
      );
    void __fieldInitializers1931();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class FinishState__StatefulReducer<void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_FINISHSTATE__STATEFULREDUCER_H

namespace x10 { namespace lang { 
template<class FMGL(T)>
class FinishState__StatefulReducer;
} } 

#ifndef X10_LANG_FINISHSTATE__STATEFULREDUCER_H_NODEPS
#define X10_LANG_FINISHSTATE__STATEFULREDUCER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Reducible.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Int.h>
#ifndef X10_LANG_FINISHSTATE__STATEFULREDUCER_H_GENERICS
#define X10_LANG_FINISHSTATE__STATEFULREDUCER_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::lang::FinishState__StatefulReducer<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::FinishState__StatefulReducer<FMGL(T)> >(), 0, sizeof(x10::lang::FinishState__StatefulReducer<FMGL(T)>))) x10::lang::FinishState__StatefulReducer<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_FINISHSTATE__STATEFULREDUCER_H_GENERICS
#ifndef X10_LANG_FINISHSTATE__STATEFULREDUCER_H_IMPLEMENTATION
#define X10_LANG_FINISHSTATE__STATEFULREDUCER_H_IMPLEMENTATION
#include <x10/lang/FinishState__StatefulReducer.h>


template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::FinishState__StatefulReducer<FMGL(T)>");
    
}


//#line 543 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10FieldDecl_c

//#line 544 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10FieldDecl_c

//#line 545 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10FieldDecl_c

//#line 546 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10FieldDecl_c

//#line 547 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::_constructor(
                          x10aux::ref<x10::lang::Reducible<FMGL(T)> > r)
{
    this->::x10::lang::Object::_constructor();
    
    //#line 542 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->x10::lang::FinishState__StatefulReducer<FMGL(T)>::__fieldInitializers1931();
    
    //#line 548 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
      FMGL(reducer) =
      r;
    
    //#line 549 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
    FMGL(T) zero =
      x10::lang::Reducible<FMGL(T)>::zero(x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                                                              FMGL(reducer)));
    
    //#line 550 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
      FMGL(result) =
      zero;
    
    //#line 551 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
      FMGL(resultRail) =
      x10::lang::Rail<void>::make<FMGL(T) >(x10aux::max_threads(), zero);
    
}
template<class FMGL(T)>
x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> > x10::lang::FinishState__StatefulReducer<FMGL(T)>::_make(
  x10aux::ref<x10::lang::Reducible<FMGL(T)> > r)
{
    x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::FinishState__StatefulReducer<FMGL(T)> >(), 0, sizeof(x10::lang::FinishState__StatefulReducer<FMGL(T)>))) x10::lang::FinishState__StatefulReducer<FMGL(T)>();
    this_->_constructor(r);
    return this_;
}



//#line 553 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::accept(
  FMGL(T) t) {
    
    //#line 554 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
      FMGL(result) = x10::lang::Reducible<FMGL(T)>::__apply(x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                                                                                FMGL(reducer)), 
                       ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                         FMGL(result),
                       t);
}

//#line 556 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::accept(
  FMGL(T) t,
  x10_int id) {
    
    //#line 557 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
    if (((id) >= (((x10_int)0))) && ((id) < (x10aux::max_threads())))
    {
        
        //#line 558 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        (((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
           FMGL(resultRail))->__set(x10::lang::Reducible<FMGL(T)>::__apply(x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                                                                                               FMGL(reducer)), 
                                      (((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                                         FMGL(resultRail))->__apply(id),
                                      t), id);
        
        //#line 559 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        (((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
           FMGL(workerFlag))->__set(true, id);
    }
    
}

//#line 562 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::placeMerge(
  ) {
    
    //#line 563 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 563 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (x10aux::max_threads()));
             
             //#line 563 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 564 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
            if ((((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                   FMGL(workerFlag))->__apply(i))
            {
                
                //#line 565 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
                ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                  FMGL(result) =
                  x10::lang::Reducible<FMGL(T)>::__apply(x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                                                                             FMGL(reducer)), 
                    ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                      FMGL(result),
                    (((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                       FMGL(resultRail))->__apply(i));
                
                //#line 566 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
                (((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                   FMGL(resultRail))->__set(x10::lang::Reducible<FMGL(T)>::zero(x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                                                                                                    FMGL(reducer))), i);
            }
            
        }
    }
    
}

//#line 570 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::lang::FinishState__StatefulReducer<FMGL(T)>::result(
  ) {
    
    //#line 570 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
             FMGL(result);
    
}

//#line 571 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::reset(
  ) {
    
    //#line 572 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
      FMGL(result) = x10::lang::Reducible<FMGL(T)>::zero(x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
                                                                             FMGL(reducer)));
}

//#line 542 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >
  x10::lang::FinishState__StatefulReducer<FMGL(T)>::x10__lang__FinishState__StatefulReducer____x10__lang__FinishState__StatefulReducer__this(
  ) {
    
    //#line 542 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this);
    
}

//#line 542 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::__fieldInitializers1931(
  ) {
    
    //#line 542 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
      FMGL(resultRail) = x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Rail<FMGL(T) > > >(X10_NULL);
    
    //#line 542 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >)this)->
      FMGL(workerFlag) = x10::lang::Rail<void>::make<x10_boolean >(x10aux::max_threads(), false);
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::lang::FinishState__StatefulReducer<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::lang::FinishState__StatefulReducer<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(reducer));
    buf.write(this->FMGL(result));
    buf.write(this->FMGL(resultRail));
    buf.write(this->FMGL(workerFlag));
    
}

template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(reducer) = buf.read<x10aux::ref<x10::lang::Reducible<FMGL(T)> > >();
    FMGL(result) = buf.read<FMGL(T)>();
    FMGL(resultRail) = buf.read<x10aux::ref<x10::lang::Rail<FMGL(T) > > >();
    FMGL(workerFlag) = buf.read<x10aux::ref<x10::lang::Rail<x10_boolean > > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::FinishState__StatefulReducer<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::FinishState__StatefulReducer<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::FinishState__StatefulReducer<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.FinishState.StatefulReducer";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 1, params, variances);
}
#endif // X10_LANG_FINISHSTATE__STATEFULREDUCER_H_IMPLEMENTATION
#endif // __X10_LANG_FINISHSTATE__STATEFULREDUCER_H_NODEPS
